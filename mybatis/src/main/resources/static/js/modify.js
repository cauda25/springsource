// 삭제 클릭시 actionForm 전송
// 정밀로 삭제하시겠습니까?
const actionForm = document.querySelector("#actionForm");

document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    actionForm.action = "/book/remove";
    actionForm.submit();
  }
});

document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  // 목록 클릭 시 a 태그 기능 중지
  e.preventDefault();
  // actionFrom 에서 id 요소 제거 하기
  actionForm.querySelector("[name='id']").remove();
  // actionFrom method는 get 변경하기
  actionForm.method = "get";
  // actionFrom method는 list 변경하기
  actionForm.action = "/book/list";
  // actionForm.submit();
  actionForm.submit();

  // e.preventDefault();
  // const id = document.querySelector("[name='id']");
  // id.remove();
  // actionForm.setAttribute("method", "get");
  // actionForm.action = "/book/list";
  // actionForm.submit();
});
