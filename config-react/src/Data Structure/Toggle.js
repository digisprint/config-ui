import React, { useState } from 'react'
import { Tabledata } from '../container/TableData/TableData'

function Toggle (props) {
    const {handleOpen, show, tableData } = props
    console.log('tabledata',tableData)
return (
    <div>
        {show? null: <Tabledata tableData={tableData}/>}        
    </div>
)
}
export default Toggle