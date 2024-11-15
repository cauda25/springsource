// Remove 클릭 시
// actionFrom action 수정
const actionFrom = document.querySelector("#actionFrom");

document.querySelector(".btn-outline-danger").addEventListener("click", () => {
  if (!confirm("정말로 삭제하시겠습니까?")) {
    return;
  }
  actionFrom.action = "/board/remove";
  actionFrom.submit();
});

// List 클릭 시
// actionFrom action 수정(get)
// gno 삭제
document.querySelector(".btn-outline-info").addEventListener("click", (e) => {
  actionFrom.method = "get";
  actionFrom.querySelector("[name='bno']").remove;
  actionFrom.submit();
});
