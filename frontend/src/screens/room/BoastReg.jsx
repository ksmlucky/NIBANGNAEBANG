/* eslint-disable */
import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';


function BoastReg(){

    return (
        <div className="container flex">
            <div className = "Reg">
                <div class Name = "Reg_editor">
                    {/* <h2>Using CKEditor 5 build in React</h2> */}
                    <CKEditor
                        editor={ ClassicEditor }
                        data="<p>Hello from CKEditor 5!</p>"
                        onReady={ editor => {
                            // You can store the "editor" and use when it is needed.
                            console.log( 'Editor is ready to use!', editor );
                        } }
                        onChange={ ( event, editor ) => {
                            const data = editor.getData();
                            // console.log( { event, editor, data } );
                            console.log(data);
                        } }
                        onBlur={ ( event, editor ) => {
                            // console.log( 'Blur.', editor );
                        } }
                        onFocus={ ( event, editor ) => {
                            // console.log( 'Focus.', editor );
                        } }
                    />
                </div>
                <div className = "Reg_button">
                    <div className = "Reg_button_complete">
                        <Button>
                            등록하기
                        </Button>
                    </div>
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