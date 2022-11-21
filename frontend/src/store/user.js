import { createSlice } from "@reduxjs/toolkit";
import { nominalTypeHack } from "prop-types";

const initialState = {
  auth: null,
  name: null,
  birth: null,
  email: null,
  joinDate: null,
  nickname: null,
  profileImg: null,
  tel: null,
  provider: null,
  providerId: null,
  di: null,
  // isSocial: null,
};
export const userSlice = createSlice({
  name: "user",
  // initialState: initialUserState,
  initialState,
  reducers: {
    setName: (state, action) => {
      state.name = action.payload;
    },
    setBirth: (state, action) => {
      state.birth = action.payload;
    },
    setProvider: (state, action) => {
      state.provider = action.payload;
    },
    setProviderId: (state, action) => {
      state.providerId = action.payload;
    },
    setDi: (state, action) => {
      state.di = action.payload;
    },
    setNickname: (state, action) => {
      state.nickname = action.payload;
    },
    setTel: (state, action) => {
      state.tel = action.payload.tel;
    },
    setProfileImg: (state, action) => {
      state.profileImg = action.payload;
    },
    setUserInfo: (state, action) => {
      state.auth = action.payload.auth;
      state.email = action.payload.email;
      state.name = action.payload.name;
      state.birth = action.payload.birth;
      state.joinDate = action.payload.joinDate.substr(0, 10);
      state.nickname = action.payload.nickname;
      state.profileImg = `data:image/png;base64,${action.payload.profileImg}`;
      state.tel = action.payload.tel;
      state.provider = action.payload.provider;
      state.providerId = action.payload.providerId;
      state.di = action.payload.di;
      // state.isSocial = action.payload.isSocial;
    },
    updateUserInfo: (state, action) => {
      state.birth = action.payload.birth;
      state.nickname = action.payload.nickname;
      state.tel = action.payload.tel;
    },
    reset: (state) => {
      Object.assign(state, initialState);
    },
    // updateAccessToken: (state, action) => {
    //   state.accessToken = action.payload.accessToken;
    // },
    findId: (state, action) => {
      state.value = action.payload;
    },
    findPw: (state, action) => {
      state.value = action.payload;
    },
  },
});
export const selectEmail = (state) => state.user.email;
export const selectProfile = (state) => state.user;

export const {
  setUserInfo,
  setProfileImg,
  updateUserInfo,
  reset,
  setEmail,
  setTel,
  setNickname,
  findId,
  findPw,
  setProvider,
  setProviderId,
  setName,
  setBirth,
  setDi,
} = userSlice.actions;

// export const setProvider = userSlice.actions.setProvider;
// export const setProviderId = userSlice.actions.setProviderId;

export default userSlice.reducer;
