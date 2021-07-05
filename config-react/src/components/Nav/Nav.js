import React, { Component } from 'react'
import logo from '../../assests/DigiSprint-Logo.png'
import './Nav.css'

export class Nav extends Component {
    render() {
        return (
            <React.Fragment>
                <nav class="navbar fixed-top flex-md-nowrap py-3 shadow pr-nav">
                    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">
                        <img src={logo} />
                    </a>
                    {/* <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search" /> */}
                    <ul class="navbar-nav px-3">
                        <li class="nav-item text-nowrap">
                        <a class="nav-link" href="#">Sign out</a>
                        </li>
                    </ul>
                </nav>

 <div class="container-fluid">

    </div>
            </React.Fragment>
        )
    }
}

export default Nav
