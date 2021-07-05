import React,{useState, useMemo} from 'react'
import {Link , Route} from 'react-router-dom'
import Toggle from '../../Data Structure/Toggle'
import  './dataList.css'
import BlankTable from '../../container/Blank-Input/config-list-blankTable'
import { Button } from 'react-bootstrap'
//import { Tabledata } from '../../container/TableData/TableData'
import SearchBar from '../../components/SearchBar/SearchBar'

const DataList = (props) => {
    const  {list, history} = props
    console.log('props',props)
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
                <SearchBar list={list}/>
                    {list && list.body.map((ele)=> {
                        return(
                            <div>
                            <Link onClick={(e) => {handleChange(e)}}>{ele.key}</Link>                            
                            </div>
                        )
                    })}
                </div>
                <div className="col-10">
                 <button className="btn btn-primary" onClick={handleChang}>Add Config</button>
                {blank? <BlankTable handleChang={handleChang}/>:null}
                    {(tableData!==undefined)?<Toggle tableData={tableData} show={show}/>:null}
                </div>
                <Route path ="/BlankTable" component ={BlankTable} />
            </div>
        </div>
    )

}

export default DataList
