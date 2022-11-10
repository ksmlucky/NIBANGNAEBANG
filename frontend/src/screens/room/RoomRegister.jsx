import React, {useState} from 'react';
import Questions from './Questions';
import "./RoomRegister.scss";


function RoomRegister() {
  const [id, setId] = useState(0);
  const parentFnc = (idx) => {
    setId(idx);
  };
  return (
    <div className="container flex">
      <Questions id={id} getId={parentFnc} />
    </div>
  );
}
export default RoomRegister;