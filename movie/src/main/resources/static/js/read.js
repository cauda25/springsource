// 전체 리뷰 보여줄 영역 가져오기
const reviewList = document.querySelector(".review-list");
const reviewForm = document.querySelector("#reviewForm");

//날짜 처리 함수
const formaDateTime = (str) => {
  const date = new Date(str);

  return (
    date.getFullYear() +
    "/" +
    (date.getMonth() + 1) +
    "/" +
    date.getDate() +
    " " +
    date.getHours() +
    ":" +
    date.getMinutes()
  );
};

// 영화의 전체 리뷰 가져오기
const reviewLoaded = () => {
  fetch(`/reviews/${mno}/all`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // 도착한 data 화면에 보여주기

      document.querySelector(".review-cnt").innerHTML = data.length;
      if (data.length > 0) {
        reviewList.classList.remove("hidden");
      }
      let result = "";
      data.forEach((review) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom py-2 review-row" data-rno="${review.reviewNo}">`;
        result += `<div class="flex-grow-1 align-self-center"><div>`;
        result += `<span class="font-semibold">${review.text}</span>`;
        result += `</div><div class="small text-muted">`;
        result += `<span class="d-inline-block mr-3">${review.nickname}</span>`;
        result += `평점 : `;
        result += `<span class="grade">${review.grade}</span><div class="starrr"></div>`;
        result += `</div><div class="text-muted">`;
        result += `<span class="small">${formaDateTime(review.regDate)}</span>`;
        result += ` </div></div> `;

        // 리뷰 작성자 == 로그인 사용자
        if (review.email === loginUser) {
          result += `<div class="d-flex flex-column align-self-center">`;
          result += `<div class="mb-2"><button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
          result += `<div class="mb-2"><button class="btn btn-outline-success btn-sm">수정</button></div>`;
          result += `</div>`;
        }
        result += `</div>`;
      });
      // 리뷰영역에 보여주기
      reviewList.innerHTML = result;
    });
};
reviewLoaded();

// 리뷰 작성 및 수정
reviewForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const reviewNo = reviewForm.reviewNo.value;
  const email = reviewForm.email.value;
  const nickname = reviewForm.nickname.value;
  const text = reviewForm.text.value;
  const mmid = reviewForm.mmid.value;

  const review = {
    reviewNo: reviewNo,
    text: text,
    grade: grade || 0,
    mno: mno,
    mmid: mmid,
    email: email,
    nickname: nickname,
  };

  if (!reviewNo) {
    // 신규 작성
    fetch(`/reviews/${mno}}`, {
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfValue,
      },
      body: JSON.stringify(review),
      method: "post",
    })
      .then((response) => response.text())
      .then((data) => {
        if (data) {
          reviewForm.reviewNo.value = "";
          reviewForm.email.value = "";
          reviewForm.nickname.value = "";
          reviewForm.text.value = "";
          reviewForm
            .querySelector(".starrr a:nth-child(" + grade + ")")
            .click();
          alert(data + " 리뷰를 작성했습니다.");
          reviewLoaded();
        }
      });
  } else {
    // 수정
    fetch(`/reviews/${mno}/${reviewNo}`, {
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfValue,
      },
      body: JSON.stringify(review),
      method: "put",
    })
      .then((response) => response.text())
      .then((data) => {
        if (data) {
          reviewForm.reviewNo.value = "";
          reviewForm.email.value = "";
          reviewForm.nickname.value = "";
          reviewForm.text.value = "";
          reviewForm
            .querySelector(".starrr a:nth-child(" + data.grade + ")")
            .click();

          reviewForm.querySelector(".btn-outline-danger").innerHTML =
            "리뷰 등록";
          alert(data + " 리뷰가 수정되었습니다.");
          reviewLoaded();
        }
      });
  }
});

// 리뷰 삭제 및 조회
reviewList.addEventListener("click", (e) => {
  // 어느 삭제 버튼에서 발생한 이벤트인가
  const btn = e.target;

  // reviewNo 가져오기(data-rno 값)
  const reviewNo = btn.closest(".review-row").dataset.rno;

  // 작성자 emil
  const email = reviewForm.email.value;
  const form = new FormData();
  form.append("email", email);

  if (btn.classList.contains("btn-outline-danger")) {
    if (!confirm("리뷰를 삭제하시겠습니까?")) return;
    fetch(`/reviews/${mno}/${reviewNo}`, {
      method: "delete",
      headers: { "X-CSRF-TOKEN": csrfValue },
      body: form,
    })
      .then((response) => response.text())
      .then((data) => {
        if (data) {
          alert(data + " 리뷰가 삭제되었습니다.");
          reviewLoaded();
        }
      });
  } else if (btn.classList.contains("btn-outline-success")) {
    // 수정할 review 화면에 보여주기
    fetch(`/reviews/${mno}/${reviewNo}`)
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          console.log(data);
          // 도착한 데이터 reviewForm안에 보여주기

          reviewForm.reviewNo.value = `${data.reviewNo}`;
          reviewForm.email.value = `${data.email}`;
          reviewForm.nickname.value = `${data.nickname}`;
          reviewForm.text.value = `${data.text}`;
          reviewForm
            .querySelector(".starrr a:nth-child(" + data.grade + ")")
            .click();

          reviewForm.querySelector(".btn-outline-danger").innerHTML =
            "리뷰 수정";
        }
      });
  }
});

// 이미지 모달 요소 가져오기
const imgModal = document.querySelector("#imgModal");

if (imgModal) {
  imgModal.addEventListener("show.bs.modal", (e) => {
    // 모달을 뜨게 만들 li 요서 가져오기
    const posterImg = e.relatedTarget;

    //data- 가져오기
    const file = posterImg.getAttribute("data-file");
    console.log(file);

    imgModal.querySelector(".modal-title").textContent = `${title}`;
    imgModal.querySelector(
      ".modal-body"
    ).innerHTML = `<img src="/upload/display?fileName=${file}&size=1" alt="" style="width:100%"/>`;
  });
}
