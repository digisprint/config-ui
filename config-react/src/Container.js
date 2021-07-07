import React, { useState, useEffect } from "react";
import "./components/Tabs/Tabs.css";
import StaticKeys from "./templates/UI/StaticKeys";
import Tabs from "./components/Tabs/Tabs";
import ConfigList from "./templates/UI/ConfigList";
import { Link, Route } from "react-router-dom";
import { request } from "./lib/request";
import { withWidth } from "@material-ui/core";
import ItemList from './ItemList'
import NavBar from "./NavBar";

export default class container extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            config: '',
            selected: '',
            selectedConfig: '',
            response: ''
        }
    }

    async componentDidMount() {
        const config = await request("global-config/config");
        this.setState({ config })
    }

    setSelected = async (selected) => {
        const { config } = this.state
        const response = await request(`global-config/config/${selected}`)
        this.setState({ selected, selectedConfig: config.body.find(i => i.urlPath === selected), response })
    }

    render() {
        console.log('this state', this.state)
        const { config, selectedConfig, selected, response } = this.state
        return (
            <div>
                <div class="container">
                    <div className="row">
                        <div className="col">
                            {config && <NavBar setSelected={this.setSelected} config={config} />}
                        </div>
                    </div>
                    <div className="row" style={{ marginTop: '60px' }}>
                        <div className="col">
                            <ItemList config={selectedConfig} selected={selected} response={response}/>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
