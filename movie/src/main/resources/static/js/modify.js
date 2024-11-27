// modifyForm 찾은 후 action = "/movie/remove"
const modifyForm = document.querySelector("#modifyForm");

if (modifyForm) {
  modifyForm.addEventListener("submit", (e) => {
    e.preventDefault();

    if (!confirm("정말 삭제하시겠습니까?")) {
      return;
    }
    modifyForm.action = "/movie/remove";
    modifyForm.submit();
  });
}

// document.querySelector(".bg-red-100").addEventListener("click", () => {
// if (confirm("정말 삭제하시겠습니까?")) {
// modifyForm.action = "/movie/remove";
// modifyForm.submit();
// }
// });
