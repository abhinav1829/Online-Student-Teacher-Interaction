function Search() {
  var search = document.getElementById("search").value;
  var tags = ["doubts", "askDoubts", "schedule_notice"];
  if (search == "") {
    alert("Blank Search Is Not Allowed.")
  }
  if (tags.includes(search)) {
    alert("Allowed.")
  }
}

function openNav() {
  if (document.getElementsByTagName("nav")[0].style.width == "0px")
    document.getElementsByTagName("nav")[0].style.width = "250px";
  else
    document.getElementsByTagName("nav")[0].style.width = "0px";
}