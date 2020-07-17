import React from 'react'
import { Toast } from 'react-bootstrap'


export const ToastMessage = props => {
    const toastCss = {
        position: 'fixed',
        top: '20px',
        right: '20px',

    }
    return (
        <div style={props.data.show ? toastCss : null}>
            <Toast className={"border border-success bg-success text-white"} show={props.data.show}>
                <Toast.Body>{props.data.message}</Toast.Body>
            </Toast>
        </div>
    )
}
