document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  if (e.keyCode == 13) {
    // alert("엔터");

    // 검색어 입력 확인
    const kw = e.target.value;
    // 없으면 메세지 띄우고 돌러보내기
    if (!kw) {
      alert("검색어를 확인해주세요");
      return;
    }
    // 있으면 keyword 가져온 후
    // searchForm 찾아서 keyword 입력값을 변경
    const searchForm = document.querySelector("#searchForm");
    searchForm.querySelector("[name='keyword']").value = kw;

    // form submit
    searchForm.submit();
  }
});
