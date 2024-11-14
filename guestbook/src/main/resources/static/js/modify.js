// Remove 클릭 시
// actionFrom action 수정
const actionFrom = document.querySelector("#actionFrom");

document.querySelector(".btn-danger").addEventListener("click", () => {
  if (!confirm("정말로 삭제하시겠습니까?")) {
    return;
  }
  actionFrom.action = "/guestbook/remove";
  actionFrom.submit();
});

// List 클릭 시
// actionFrom action 수정(get)
// gno 삭제
document.querySelector(".btn-info").addEventListener("click", (e) => {
  actionFrom.method = "get";
  actionFrom.querySelector("[name='gno']").remove;
  actionFrom.submit();
});
