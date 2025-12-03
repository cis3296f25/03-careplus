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
    phoneNumber: document.getElementById('mobileNum').value,
    ssn: document.getElementById('ssn').value,
    dob: document.getElementById('dob').value,
    usState: document.getElementById('state').value
  };

  console.log("Sending:", data);

  fetch("http://localhost:8081/USCIS-api/save", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  })
    .then(response => response.text())
    .then(result => {
      alert(result);
      window.location.href = "/DataCollectionService/datacollecting.html";
    })
    .catch(err => {
      console.error(err);
      alert("Error sending data to backend");
    });

    
}
