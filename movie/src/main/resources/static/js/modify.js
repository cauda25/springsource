// 포스터 추사 시 삭제 기능
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();

  const element = e.target.closest("li");
  // 서버 저장한 포스터 삭제 x
  if (confirm("정말로 이미지를 삭제하시겠습니까?")) {
    element.remove();
    e.stopPropagation();
  }
});
// modifyForm 찾은 후 action = "/movie/remove"
const form = document.querySelector("#actionForm");
const removeBtn = document.querySelector("#createForm .bg-red-100");
const listBtn = document.querySelector("#createForm .bg-orange-100");

if (removeBtn) {
  removeBtn.addEventListener("click", () => {
    if (!confirm("정말 삭제하시겠습니까?")) {
      return;
    }
    form.action = "/movie/remove";
    form.submit();
  });
}

listBtn.addEventListener("click", () => {
  form.querySelector("[name='mno']").remove();

  form.method = "get";

  form.action = "/movie/list";
  form.submit();
});
