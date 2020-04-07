function openNav() {
    document.getElementsByTagName("nav")[0].style.width = "250px";
}

function closeNav() {
    document.getElementsByTagName("nav")[0].style.width = "0px";
}

function validate_reg() {
    var reg = document.forms["regform"];

    name = reg.elements["name"].value;
    roll = reg.elements["roll"].value;
    email = reg.elements["email"].value;
    phone = reg.elements["phone"].value;
    pass1 = reg.elements["pass1"].value;
    pass2 = reg.elements["pass2"].value;

    if((/\d/.test(name))||(/[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(name))){
        alert("Invalid name");
        return false;
    }

    if (roll < 1101 || roll > 41099) {
        alert("Invalid roll number");
        return false;
    }

    if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))){
        alert("Invalid email address");
        return false;
    }

    if (phone<=0||phone.toString().length !== 10) {
        alert("Invalid phone number");
        return false;
    }

    if(pass1!=pass2){
        alert("Passwords don't match");
        return false;
    }
}