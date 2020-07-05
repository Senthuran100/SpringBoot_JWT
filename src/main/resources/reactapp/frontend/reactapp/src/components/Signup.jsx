import React from 'react'
import { form } from "react-bootstrap";
export default function Signup() {



    return (
        <div>
            <div class="container">
                <div class="card" >
                    <div class="card-body">
                        <h1>User Signup</h1>
                        <form>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Username</label>
                                <input type="text" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter Username" />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="password" placeholder="Enter Password" />
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
