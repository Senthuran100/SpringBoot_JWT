import React,{useState,useEffect} from 'react'
import { form } from "react-bootstrap";
import axios from 'axios';

export default function Signup() {

    const [username, setUsername] = useState("");
    const [password, setPassword] =useState("");
    const handleSubmit = (e) =>{
        e.preventDefault()
        alert( "Username "+ username + " Password "+password);
        axios.post('http://localhost:8080/sign-up',{
            username:username,
            password:password})
    };

    return (
        <div>
            <div class="container">
                <div class="card" >
                    <div class="card-body">
                        <h1>User Signup</h1>
                        <form onSubmit={handleSubmit}>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Username</label>
                                <input type="text" class="form-control" value={username} onChange={e => setUsername(e.target.value)} id="username" aria-describedby="emailHelp" placeholder="Enter Username" required/>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Enter Password" required/>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
