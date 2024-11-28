document.querySelector(".uploadResult ul").addEventListener("click", (e) => {
  e.preventDefault();

  const element = e.target.closest("a");

  const removeLi = e.target.closest("li");

  const filePath = element.getAttribute("href");

  let formDate = new FormData();
  formDate.append("filePath", filePath);
  fetch("/upload/remove", {
    method: "post",
    body: formDate,
  })
    .then((response) => {
      if (!response.ok) throw new Error("에러발생");
      return response.text();
    })
    .then((data) => {
      // 화면 이미지 제거
      if (data) removeLi.remove();
    });
});
