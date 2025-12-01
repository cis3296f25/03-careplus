let currentStep = 1;

function nextStep(step) {
  document.getElementById(`form-step-${step}`).classList.remove('active');
  document.getElementById(`form-step-${step+1}`).classList.add('active');
  document.getElementById(`step${step}`).classList.remove('active');
  document.getElementById(`step${step+1}`).classList.add('active');
}

function prevStep(step) {
  document.getElementById(`form-step-${step+1}`).classList.remove('active');
  document.getElementById(`form-step-${step}`).classList.add('active');
  document.getElementById(`step${step+1}`).classList.remove('active');
  document.getElementById(`step${step}`).classList.add('active');
}

function submitForm() {
  const ssn = document.getElementById('ssn').value.trim();

  const lastDigit = ssn.charAt(ssn.length - 1);
  if (!/[0-5]/.test(lastDigit)) {
    alert("SSN last digit must be between 0 and 5.");
    return;
  }

  const data = {
    fullName: document.getElementById('fullName').value,
    email: document.getElementById('email').value,
    gender: document.getElementById('gender').value,
    mobileNum: document.getElementById('mobileNum').value,
    ssn: ssn,
    dob: document.getElementById('dob').value,
    state: document.getElementById('state').value
  };

  console.log("Form Data Submitted:", data);
  alert("Form submitted successfully!");

  window.location.href = "/DataCollectionService/datacollecting.html";
}
