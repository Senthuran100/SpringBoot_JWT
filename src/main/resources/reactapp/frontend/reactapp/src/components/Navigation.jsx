import React from 'react'
import {Nav,Navbar} from 'react-bootstrap';

export default function Navigation() {
    return (
        <div>
            <Navbar bg="light" expand="lg">
                <Navbar.Brand href="home">JWT-Throttle</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href="sign-up">Demo</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        </div>
    )
}
