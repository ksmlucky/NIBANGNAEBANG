/* eslint-disable */
import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import { CKEditor } from "ckeditor4-react";
import BoastRoom from "@screens/room/BoastRoom";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import "./BoastReg.scss";


function BoastReg(){
    const array = {};
    return (
        <div className="container flex">
            <div className="Reg flex">
                <div className="Reg_title shBold fs-56">내 방을 자랑해보자</div>
                <div className = "Reg_editor">
                    {/* <h2>Using CKEditor 5 build in React</h2> */}
                    <CKEditor
                        editor={ ClassicEditor }
                        data="<p></p>"
                        onReady={ editor => {
                            // You can store the "editor" and use when it is needed.
                            console.log( 'Editor is ready to use!', editor );
                        } }
                        onChange={ ( event, editor ) => {
                            const data = editor.getData();
                            // console.log( { event, editor, data } );
                            console.log(data);
                        } }
                        // onBlur={ ( event, editor ) => {
                        //     console.log('Blur',editor);
                        // } }
                        // onFocus={ ( event, editor ) => {
                        //     console.log('Focus',editor);
                        // } }
                        config={{
                            height: 500,
                            uiColor: "#2c4b48",
                            width: 800,
                            resize_enabled: false,
                            toolbar: [
                                ["Format", "Font", "FontSize"],
                                ["Bold", "Italic"],
                                ["Undo", "Redo"],
                            ],
                            removePlugins: "image, elementspath",
                        }}
                    />
                </div>
                <div className = "Reg_button">
                    <div className = "Reg_button_complete">
                        <Button>
                            등록하기
                        </Button>
                    </div>
                    {/* <div className = "Reg_button_clear">
                        <Button>
                            내용 지우기
                        </Button>
                    </div> */}
                    <div className = "Reg_button_cancle">
                        <Button>
                            취소하기
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default BoastReg;