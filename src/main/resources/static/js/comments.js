const commentsButton = document.querySelectorAll('.comments-btn');


for (const currentButton of commentsButton) {
    currentButton.addEventListener('click', loadComments);
}

async function loadComments(e) {
    let id = e.target.value;
    let container = document.getElementById(`comments-${id}`);
    container.innerHTML = '';

    const comments = await (await fetch(`http://localhost:8080/api/${id}/comments`)).json();
    let counter = 1;
    Object.values(comments).forEach(comment => {
        const p1 = document.createElement("p");
        p1.textContent = counter +": "+ `${comment.author}: ${comment.textContent}`;
        counter++;
        container.appendChild(p1);
    });
}




function cleanComments(e) {
    let id = e.target.value;
    let container = document.getElementById(`comments-${id}`);
    container.innerHTML = '';
}


const whiteSpace = document.querySelectorAll('.form-my');
for (const whiteSpaceElement of whiteSpace) {
    whiteSpaceElement.addEventListener('click', showSubmitComment);
}

function showSubmitComment(e) {
    let id = e.target.value;
    console.log(id);
    let button = document.getElementById(`submit-btn-${id}`);
}



