document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded event fired");
    var successMessage = document.getElementById("successDiv");
    var errorMessage = document.getElementById("errorDiv");
    if (successMessage && successMessage.textContent.trim() !== "") {
        alert(successMessage.textContent);
        setTimeout(function() {
            successMessage.style.display = "none";
        }, 3000);
    } else if (errorMessage && errorMessage.textContent.trim() !== "") {
        alert(errorMessage.textContent);
        setTimeout(function() {
            errorMessage.style.display = "none";
        }, 4000);
    }
});
document.addEventListener("DOMContentLoaded", function() {
    var jobSuccessMessage = document.getElementById("jobSuccessMessage");
    var jobErrorMessage = document.getElementById("jobErrorMessage");
    if (jobSuccessMessage && jobSuccessMessage.textContent.trim() !== "") {
        alert(jobSuccessMessage.textContent);
        setTimeout(function() {
            jobSuccessMessage.style.display = "none";
        }, 3000);
    } else if (jobErrorMessage && jobErrorMessage.textContent.trim() !== "") {
        alert(jobErrorMessage.textContent);
        setTimeout(function() {
            jobErrorMessage.style.display = "none";
        }, 4000);
    }
});
document.addEventListener("DOMContentLoaded", function() {
    var employeSuccessMessage = document.getElementById("employeSuccessMessage");
    var emailExistsErrorMessage = document.getElementById("emailExistsErrorMessage");
    if (employeSuccessMessage && employeSuccessMessage.textContent.trim() !== "") {
        alertAndHide(employeSuccessMessage, 3000);
    }
    if (emailExistsErrorMessage && emailExistsErrorMessage.textContent.trim() !== "") {
        alertAndHide(emailExistsErrorMessage, 4000);
    }
});
function alertAndHide(element, timeout) {
    if (element) {
        alert(element.textContent);
        setTimeout(function() {
            element.style.display = "none";
        }, timeout);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.btn-modifier').forEach(button => {
        button.addEventListener('click', function() {
            const employeId = this.getAttribute('data-id');
            window.location.href = `/editEmploye/${employeId}`;
        });
    });
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function() {
            const employeId = this.getAttribute('data-id');
            const confirmationMessage = 'Êtes-vous sûr de vouloir supprimer cet employé ?';
            if (confirm(confirmationMessage)) {
                fetch(`/deleteEmploye/${employeId}`, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (response.ok) {
                            const employeRow = this.closest('tr');
                            employeRow.remove();
                        } else {
                            console.error('Errore durante l\'eliminazione dell\'employé');
                        }
                    })
                    .catch(error => {
                        console.error('Errore durante la richiesta di eliminazione:', error);
                    });
            }
        });
    });
    var updateFlag = document.getElementById('employeeUpdated');
    if (updateFlag) {
        alert('L\'employé a été modifié avec succès!');
    }
});
