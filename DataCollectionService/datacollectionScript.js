const form = document.querySelector(".form-wizard");
const progress = form.querySelector(".progress");
const stepsContainer = form.querySelector(".steps-container");
const steps = form.querySelectorAll(".step");
const stepIndicators = form.querySelectorAll(".progress-container li");
const prevButton = form.querySelector(".prev-btn");
const nextButton = form.querySelector(".next-btn");
const submitButton = form.querySelector(".submit-btn");

document.documentElement.style.setProperty("--steps", stepIndicators.length);

let currentStep = 0;
let globalCaseNo = null;
let globalAppId = null;

const updateProgress = () => {
  let width = currentStep / (steps.length - 1);
  progress.style.transform = `scaleX(${width})`;
  stepsContainer.style.height = steps[currentStep].offsetHeight + "px";

  stepIndicators.forEach((indicator, index) => {
    indicator.classList.toggle("current", currentStep === index);
    indicator.classList.toggle("done", currentStep > index);
  });

  steps.forEach((step, index) => {
    const percentage = document.documentElement.dir === "rtl" ? 100 : -100;
    step.style.transform = `translateX(${currentStep * percentage}%)`;
    step.classList.toggle("current", currentStep === index);
  });

  updateButtons();
};

const updateButtons = () => {
  prevButton.hidden = currentStep === 0;
  nextButton.hidden = currentStep >= steps.length - 1;
  submitButton.hidden = !nextButton.hidden;
};

const isValidStep = () => {
  const fields = steps[currentStep].querySelectorAll("input, textarea");
  return [...fields].every((field) => field.reportValidity());
};

async function safeResponse(res) {
  const text = await res.text();
  if (!res.ok) throw new Error(text);
  try { return JSON.parse(text); }
  catch { return text; }
}

async function generateCaseNo(appId) {
  const res = await fetch(`http://localhost:8082/data/caseNo/${appId}`, {
    method: "POST"
  });
  return safeResponse(res);
}

async function savePlan(caseNo, planName, appId) {
  const body = { caseNo, planId: 1, appId, planName };
  const res = await fetch("http://localhost:8082/data/update", {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  });
  return safeResponse(res);
}

async function saveIncome(caseNo, income) {
  const res = await fetch("http://localhost:8082/data/saveIncome", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      incomeId: null,
      caseNo,
      empIncome: parseFloat(income),
      propertyIncome: 0
    })
  });
  return safeResponse(res);
}

async function saveEducation(caseNo, qualification) {
  const res = await fetch("http://localhost:8082/data/saveEducation", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      educationId: null,
      caseNo,
      highestDegree: qualification,
      completionYear: 0
    })
  });
  return safeResponse(res);
}

async function saveChildren(caseNo, count) {
  const num = parseInt(count);
  if (isNaN(num) || num <= 0) return;

  const childrenList = [];
  for (let i = 1; i <= num; i++) {
    childrenList.push({
      caseNo,
      childId: i,
      childDOB: "2020-01-01",
      childSSN: 111111111 + i
    });
  }

  const res = await fetch("http://localhost:8082/data/saveChildren", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(childrenList)
  });

  return safeResponse(res);
}

const inputs = form.querySelectorAll("input, textarea");
inputs.forEach((input) =>
  input.addEventListener("focus", (e) => {
    const focusedElement = e.target;
    const focusedStep = [...steps].findIndex((step) =>
      step.contains(focusedElement)
    );

    if (focusedStep !== -1 && focusedStep !== currentStep) {
      if (!isValidStep()) return;
      currentStep = focusedStep;
      updateProgress();
    }

    stepsContainer.scrollTop = 0;
    stepsContainer.scrollLeft = 0;
  })
);

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  if (!form.checkValidity()) return;

  submitButton.disabled = true;
  submitButton.textContent = "Submitting...";

  globalAppId = parseInt(document.getElementById("ApplicationID").value);
  if (isNaN(globalAppId)) {
    alert("Application ID must be a number");
    submitButton.disabled = false;
    submitButton.textContent = "Submit";
    return;
  }

  const planName = document.getElementById("plan").value;
  const income = document.getElementById("income").value;
  const edu = document.getElementById("education").value;
  const children = document.getElementById("children").value;

  try {
    globalCaseNo = await generateCaseNo(globalAppId);
    await savePlan(globalCaseNo, planName, globalAppId);
    await saveIncome(globalCaseNo, income);
    await saveEducation(globalCaseNo, edu);
    await saveChildren(globalCaseNo, children);

    form.querySelector(".completed").hidden = false;
    form.querySelector(".completed p").textContent =
      "Your case number is " + globalCaseNo;

  } catch (error) {
    alert("Error sending data to backend: " + error);
  }
});

prevButton.addEventListener("click", (e) => {
  e.preventDefault();
  if (currentStep > 0) {
    currentStep--;
    updateProgress();
  }
});

nextButton.addEventListener("click", (e) => {
  e.preventDefault();
  if (!isValidStep()) return;
  if (currentStep < steps.length - 1) {
    currentStep++;
    updateProgress();
  }
});

updateProgress();
