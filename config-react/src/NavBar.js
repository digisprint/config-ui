import React, { useState, useEffect } from "react";
import "./components/Tabs/Tabs.css";
import StaticKeys from "./templates/UI/StaticKeys";
import Tabs from "./components/Tabs/Tabs";
import ConfigList from "./templates/UI/ConfigList";
import { Link, Route } from "react-router-dom";
import { withWidth } from "@material-ui/core";


export default function NavBar(props) {

  const displayNavigation = () => {
    const { config, setSelected } = props
    return (config && config.body && config.body.map(item => (
      <li class="nav-item">
        <Link class="nav-link" href="#" to={`/${item.urlPath}`} onClick={() => setSelected(item.urlPath)}>
          {item.displayName}
        </Link>
      </li>
    )))
  }

  return (
    <div>
      <nav
        class="navbar navbar-expand-lg"
        style={{ marginTop: "94px", marginLeft: "5.5rem" }}
      >
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            {displayNavigation()}
          </ul>
        </div>
      </nav>
    </div>
  )
}
