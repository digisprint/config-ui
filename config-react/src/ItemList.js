import React from "react";
import DataListStatic from './templates/UI/DataListStatic'
import { ModalContainer } from './container/Modals/Modals'
import { request } from "./lib/request";

export default class ItemList extends React.PureComponent {

    constructor(props) {
        super(props)
    }

    render() {
        console.log('item list')
        console.log(this.props)
        const { response } = this.props
        return (
            <div>
                hello
                {/* <DataListStatic handleOpen={() => {}} list={response}/>      */}
                {/* <ModalContainer modalState = {true} /> */}
            </div>
        )
    }
}