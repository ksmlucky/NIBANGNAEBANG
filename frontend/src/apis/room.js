import { API, API_USER } from "./index";

export const getBoastRoom = async () => {
  const res = await API_USER.get(``);
};

export const registerRoom = async (body) => {
  const res = await API.post("business-service/api/article/AT", body, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
    },
  });
  return res.data;
};

export const getRoomDetail = async (roomId) => {
  const res = await API.get(`business-service/api/article/${roomId}`, {
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
    },
  });
  return res.data;
};

export const getRoomList = async (params) => {
  const res = await API.get(
    `business-service/api/article?category=transfer&page=${params.roomPage}&size=4&sorting=${params.roomSort}&orderBy=${params.roomOrder}`,
    {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
    }
  );
  return res.data;
};

export const getRoomListAd = async (params) => {
  const res = await API.get(
    `business-service/api/article?category=transfer&page=${params.roomPage}&size=4&sorting=${params.roomSort}&orderBy=${params.roomOrder}&address=${params.roomAddress}`,
    {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
    }
  );
  return res.data;
};

export const ex = () => {};
