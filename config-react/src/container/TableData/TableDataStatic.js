import React,{useState} from 'react'
import {Icon} from '../../components/Icons/Icons'
import {TableBody} from '../../components/TableBody/TableBody'
import {TableColumn} from '../../components/TableColumn/TableColumn'
import {TableHead} from '../../components/TableHead/TableHead'
import {TableHeadCol} from '../../components/TableHeadCol/TableHeadCol'
import {TableHeadRow} from '../../components/TableHeadRow/TableHeadRow';
import { BsPencil,BsFillTrashFill,BsClipboardData } from "react-icons/bs";
import swal from 'sweetalert'
import axios from 'axios'

export const TableDataStatic=(props)=>{
    const {tableData} = props
    const [inputList, setInputList] = useState(tableData)
    const datas = tableData.body

    const handleRemoveClick = (id) => {
        const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                    const URL = `http://35.224.205.52:8080/config/staticKeys/${id}`
                    console.log('id',URL)
                    const options = {
                        headers: {'X-Requested-With': 'XMLHttpRequest'}
                      };
                      //axios.post('/save', { a: 10 }, options);
                    axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
                    axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
                
                    axios.delete(PROXY_URL + URL, options)
                    .then((response)=> {
                        const result = response.data
                        swal("Good job!", "You clicked the button!", "success");  
                        window.location.reload();
                    })
                    
                    .catch((err)=>{
                        swal(err.message)
                    })
    
      }

    console.log('inputList',inputList)
    return (
    <div style={{'borderTop': '2px solid #eee'}} className="container p-3">
        
    <table className="table table-hover border container" style={{width:'80%', 'backgroundColor': '#eee'}}>
        <TableHead>
            <TableHeadRow>
                <TableHeadCol>Key</TableHeadCol>
                <TableHeadCol>Value</TableHeadCol>
            </TableHeadRow>
        </TableHead>
        <TableBody>
                    <TableHeadRow>
                <TableColumn>{inputList.key}</TableColumn>
                <TableColumn>{inputList.value}</TableColumn>
                <TableColumn>
                    <Icon handleOpen = {props.handleOpen}><BsPencil/></Icon>
                     <BsFillTrashFill style={{'color': 'red', 'cursor': 'pointer'}}
                      className="mr-3"
                      onClick={() => handleRemoveClick(inputList.key)}/>
                </TableColumn>
            </TableHeadRow>                
        </TableBody>
    </table>
    </div>
    )
}