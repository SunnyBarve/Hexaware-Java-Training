// Array to store students
let students = [];

// Function to add a student
function addStudent() {
  const name = document.getElementById("name").value;
  const age = document.getElementById("age").value;
  const grade = document.getElementById("grade").value;

  if (name === "" || age === "" || grade === "") {
    alert("Please fill in all fields");
    return;
  }

  const student = {
    name: name,
    age: parseInt(age),
    grade: grade,
  };

  students.push(student);
  alert("Student added successfully!");
  clearFields();
}

// Function to display students
function viewStudents() {
  const studentListDiv = document.getElementById("student-list");
  studentListDiv.innerHTML = "<h2>Student List</h2>";

  if (students.length === 0) {
    studentListDiv.innerHTML += "<p>No students available</p>";
    return;
  }

  let studentTable = `<table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Grade</th>
                                </tr>
                            </thead>
                            <tbody>`;

  students.forEach((student, index) => {
    studentTable += `
            <tr>
                <td>${index + 1}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.grade}</td>
            </tr>`;
  });

  studentTable += `</tbody></table>`;
  studentListDiv.innerHTML = studentTable;
}

// Function to sort students by age in ascending order
function sortStudents() {
  students.sort((a, b) => a.age - b.age); // Sorting by age in ascending order
  viewStudents();
}

// Function to delete a student by name after confirmation
function deleteStudent() {
  const studentName = prompt(
    "Enter the name of the student you want to delete:"
  );

  if (studentName) {
    const index = students.findIndex(
      (student) => student.name.toLowerCase() === studentName.toLowerCase()
    );

    if (index !== -1) {
      const confirmation = confirm(
        `Are you sure you want to delete ${studentName}?`
      );
      if (confirmation) {
        students.splice(index, 1);
        alert(`${studentName} has been removed.`);
        viewStudents();
      }
    } else {
      alert(`Student named ${studentName} not found.`);
    }
  }
}

// Helper function to clear input fields
function clearFields() {
  document.getElementById("name").value = "";
  document.getElementById("age").value = "";
  document.getElementById("grade").value = "";
}

// Add event listeners for button clicks
document.getElementById("addButton").addEventListener("click", addStudent);
document.getElementById("viewButton").addEventListener("click", viewStudents);
document.getElementById("sortButton").addEventListener("click", sortStudents);
document
  .getElementById("deleteButton")
  .addEventListener("click", deleteStudent);

// Add event listeners for hover effects on buttons
const buttons = document.querySelectorAll("button");

buttons.forEach((button) => {
  button.addEventListener("mouseover", function () {
    button.style.backgroundColor = "#4CAF50";
  });

  button.addEventListener("mouseout", function () {
    button.style.backgroundColor = ""; // Reset to the default color
  });
});

// Function to search for a student by name and allow deletion
function searchStudent() {
  const searchName = document.getElementById("search-name").value.trim();

  if (searchName === "") {
    alert("Please enter a name to search.");
    return;
  }

  // Find the student by name
  const student = students.find(
    (student) => student.name.toLowerCase() === searchName.toLowerCase()
  );

  if (student) {
    const confirmation = confirm(
      `Student found: ${student.name}, Age: ${student.age}, Grade: ${student.grade}. Do you want to delete this student?`
    );

    if (confirmation) {
      // Find the index of the student to remove
      const index = students.findIndex(
        (s) => s.name.toLowerCase() === searchName.toLowerCase()
      );

      if (index !== -1) {
        students.splice(index, 1); // Remove the student from the array
        alert(`${searchName} has been deleted.`);
        viewStudents(); // Update the displayed list
      }
    }
  } else {
    alert(`No student found with the name: ${searchName}`);
  }

  // Clear the search field
  document.getElementById("search-name").value = "";
}
