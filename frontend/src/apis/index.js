import axios from "axios";

export const BASE_URL = "https://k7c109.p.ssafy.io:8080/";

// export const { sessionStorage } = window;
// export const at = sessionStorage.getItem("accessToken");

export const API = axios.create({
  baseURL: BASE_URL,
  headers: {},
});

export const API_USER = axios.create({
  baseURL: BASE_URL, // 기본 서버 url
  headers: {
    // "Access-Control-Allow-Origin": "https://k7c109.p.ssafy.io:8080/",
    // Authorization: `Bearer ${at}`,
    Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
    // Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6NSwiaWF0IjoxNjY4NjUzNjM5LCJleHAiOjE2Njg2NTU0Mzl9.z3N9rawf_lyr9ayMIypJHYuMBk0PxAWt7ZeyT145D-A752RNbLkPaiJVqSlfEKt33G0Z8IprxHsS9tAwizArmQ`,
  },
});

export const API_PHOTO = axios.create({
  baseURL: BASE_URL, // 기본 서버 URL
  headers: {
    "Content-Type": "multipart/form-data",
  },
});

API_USER.interceptors.response.use(
  (response) => response,
  async (error) => {
    console.log(error.response.data);
    if (
      error.response.status === 401 &&
      error.response.data.error === "Unauthorized"
    ) {
      const refreshToken = await sessionStorage.getItem("refreshToken");
      const res = await axios({
        url: `https://k7c109.p.ssafy.io:8080/auth-service/api/auth/reissuance`,
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        data: {
          refreshToken: refreshToken,
        },
      });
      const originRequest = error.config;
      console.log(originRequest);
      originRequest.headers.Authorization = `Bearer ${res.data.accessToken}`;

      sessionStorage.setItem("accessToken", res.data.accessToken);
      // sessionStorage.setItem("refreshToken", res.data.refreshToken);
      return axios(originRequest);
    }
    return Promise.reject(error);
  }
);
export const ex = () => {};
