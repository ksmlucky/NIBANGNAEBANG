import { API, API_USER } from "./index";

export const getBoastRoom = async () => {
  const res = await API_USER.get(``);
};

export const registerRoom = async (body) => {
  const res = await API.post("business-service/api/article/AT", body, {
    headers: { "X-Authorization-Id": 1 },
  });
  return res.data;
};

export const getRoomDetail = async (roomId) => {
  const res = await API.get(`business-service/api/article/${roomId}`, {
    headers: { "X-Authorization-Id": 1 },
  });
  return res.data;
};

export const getRoomList = async (params) => {
  if (params.roomAddress === undefined) {
    const res = await API.get(
      `business-service/api/article?category=transfer&page=${params.roomPage}&size=4&sorting=${params.roomSort}&orderBy=${params.roomOrder}`,
      {
        headers: { "X-Authorization-Id": 1 },
      }
    );
  } else {
    const res = await API.get(
      `business-service/api/article?category=transfer&page=${params.roomPage}&size=4&sorting=${params.roomSort}&orderBy=${params.roomOrder}&address=${params.roomAddress}`,
      {
        headers: { "X-Authorization-Id": 1 },
      }
    );
  }
  return res.data;
};

export const ex = () => {};
