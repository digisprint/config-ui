import React, { useState } from 'react'
import { TableDataStatic } from '../container/TableData/TableDataStatic'

function ToggleStatic (props) {
    const {handleOpen, show, tableData } = props
    console.log('tabledatassss',tableData)
return (
    <div>
        {show? null: <TableDataStatic tableData={tableData}  handleOpen={props.handleOpen}/>}        
    </div>
)
}
export default ToggleStatic