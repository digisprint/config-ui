import React,{useState, useMemo} from 'react'
import {Link , Route} from 'react-router-dom'
import ToggleStatic from '../../Data Structure/ToggleStatic'
import  './dataList.css'
import BlankTable from '../../container/Blank-Input/config-list-blankTable'
import { Button } from 'react-bootstrap'
//import { Tabledata } from '../../container/TableData/TableData'
import SearchBarStatic from '../../components/SearchBar/searchBarStatic'
import StaticBlankTable from '../../container/Blank-Input/staticKey-BlankTable'

const DataListStatic = (props) => {
    const  {list} = props
    //console.log('props',props)
    const [show,setShow] = useState(true)
    const [tableValues,setTableValues] = useState()
    const [tableData,setTableData] = useState()
    const [blank,setBlank] = useState(false)

    const handleChang = () => {
        setBlank(!blank)
    }

    const handleChange = (e) => {
        setTableValues(e.target.textContent)
       setShow(!show)
     }

    useMemo(()=>{
        if(tableValues && tableValues.length){
            for( let i in list.body){
                if(list.body[i].key==tableValues){
                    setTableData(list.body[i])
                   console.log('i',list.body[i])
                   break
               }
            }
            // console.log('i',tableValues)
        }
    },[tableValues])
console.log('table',tableData)

    return (
        <div class="list-group container">
            <div className="row">
                <div className="col-2 bdr-right">
                <SearchBarStatic list={list}/>
                    {list && list.body.map((ele)=> {
                        return(
                            <div>
                            <Link onClick={(e) => {handleChange(e)}}>{ele.key}</Link>                            
                            </div>
                        )
                    })}
                </div>
                <div className="col-10">
                <button className="btn btn-primary" onClick={handleChang}>Static</button>
                {blank? <StaticBlankTable handleChang={handleChang}/>:null}
                    {(tableData!==undefined)?<ToggleStatic tableData={tableData} show={show} handleOpen={props.handleOpen}/>:null}
                </div>
                <Route path ="/BlankTable" component ={BlankTable} />
            </div>
        </div>
    )

}

export default DataListStatic
