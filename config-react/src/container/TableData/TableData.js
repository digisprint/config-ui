import React , { useState }from 'react'
import {TableBody} from '../../components/TableBody/TableBody'
import {TableColumn} from '../../components/TableColumn/TableColumn'
import {TableHead} from '../../components/TableHead/TableHead'
import {TableHeadCol} from '../../components/TableHeadCol/TableHeadCol'
import {TableHeadRow} from '../../components/TableHeadRow/TableHeadRow';
import {BsFillTrashFill} from "react-icons/bs";
import  '../../templates/UI/dataList.css'
import axios from 'axios'
import swal from 'sweetalert'

export const Tabledata=(props)=>{
    const { tableData } = props
    // console.log('list',list)
    console.log('tabledata',tableData.key)

    let info = tableData.value.map((ele,i)=> {
        //console.log('ele',ele)
        const obj = {value : ele}
        return obj
        })  
    const [inputList, setInputList] = useState(info);      

  // handle input change
  const handleInputChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...inputList];
    list[index][name] = value;
    setInputList(list);
  };

  // handle click event of the Remove button
  const handleRemoveClick = index => {
    swal({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover this imaginary file!",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        const list = [...inputList];
    list.splice(index, 1);
    setInputList(list);
      } else {
        swal("Your imaginary file is safe!");
      }
    });
  };

  // handle click event of the Add button
  const handleAddClick = () => {
    setInputList([...inputList, { value: ''}]);
  };

  //handle click to remove all
  const removeAll = (id) => {
    const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                const URL = `http://35.224.205.52:8080/config/configList/${id}`
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

  const handleForm =(e)=>{
    e.preventDefault()
    
    const formData = 
    {
    configList : {
    key : tableData.key,
    value : inputList.map((ele,i)=>{
      return (
          ele.value
      )
  })

     }
    }
    
    //console.log('formData',formData)

    const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                const URL = 'http://35.224.205.52:8080/config/configList';
                const options = {
                    headers: {'X-Requested-With': 'XMLHttpRequest'}
                  };
                  //axios.post('/save', { a: 10 }, options);
                axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
                axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
            
                axios.put(PROXY_URL + URL, formData, options)
                .then((response)=> {
                    const result = response.data
                    swal("Good job!", "You clicked the button!", "success");  
                })
                
                .catch((err)=>{
                    swal(err.message)
                })

}
    
    return (
    <div className="table-responsive">
      <h3>{tableData.key}</h3>
      <form onSubmit={handleForm}>
      <table className="table align-middle table-borderless">
        <TableHead>
            <TableHeadRow>
                <TableHeadCol>Value</TableHeadCol>
            </TableHeadRow>
        </TableHead>
        <TableBody style={{ 'border-bottom': '2px solid #eee'}}>
        {inputList.map((x, i) => {
        return (
     
            <TableHeadRow>
            <TableColumn>
        <input
          name="value"
          placeholder={x.value}
          value={x.value}
          onChange={e => handleInputChange(e, i)}
          className="form-control"
        /></TableColumn>
        <TableColumn>
        <div>

          {inputList.length !== 1 && <BsFillTrashFill style={{'color': 'red', 'cursor': 'pointer'}}
            className="mr-3"
            onClick={() => handleRemoveClick(i)}/>}

          {inputList.length - 1 === i && <button className="btn btn-success" onClick={handleAddClick}>Add</button>}
          
        </div>
      </TableColumn>
        </TableHeadRow>
        );
       })}
        </TableBody>
        <div className="btn-group p-2">
          <button type="submit" class="btn btn-primary mr-2" >Update</button>
          <button type="button" class="btn btn-primary" onClick={() => {removeAll(tableData.key)}}>Remove</button>
        </div>
    </table>
    </form>
    </div>   
    )
}