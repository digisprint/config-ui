import React, { useState } from 'react'
import { BsFillTrashFill } from "react-icons/bs";
import axios from 'axios'
import swal from 'sweetalert';


function BlankTable (props) {
    const [Input1 , setInput1] = useState('')
    const [input2 , setInput2] = useState([{values:''}])

    const handleChanges =(e)=>{
        const input = e.target.name
        if(input === "Input1"){
            setInput1(e.target.value)
        }
    }

    const handleForm =(e)=>{
        e.preventDefault()

        const formData = {
          
            configList : {
               key : Input1,
               value : input2.map((ele,i)=>{
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
                axios.post(PROXY_URL + URL, formData , options)
                swal({
                    title: "successfully Uploaded",
                    buttons: true,
                  })
                .then((response)=> {
                    const result = response.data                   
                      window.location.reload();
                })                
                .catch((err)=>{
                    swal(err.message)
                })
                setInput1('')
                //setInput2('')
}

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
            const list = [...input2];
            list.splice(index, 1);
            setInput2(list);
        } else {
          swal("Your imaginary file is safe!");
        }
      });
    
    //swal("Good job!", "You clicked the button!", "success");
  };

// handle click event of the Add button
const handleAddClick = () => {
    setInput2([...input2, { value: ''}]);
  };
  
  // handle input change
  const handleInputChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...input2];
    list[index][name] = value;
    setInput2(list);
  };
    
    return(
        <div>
            <form onSubmit ={handleForm}>
                <input type ="text" placeholder = 'Enter the key' 
                className ="form-control" onChange ={handleChanges} 
                value ={Input1} name = 'Input1'
                style={{'width': '36%'}}
                />
                {input2.map((item, i) => {
                    return (
                    <div class="col-8 mt-2">
                    <div key={i} class="row input-group mb-3">
                         <div>
                         <input
                          name="value"
                          placeholder='Enter the value'
                          value={item.value}
                          onChange={e => handleInputChange(e, i)}
                          className="form-control"                          
                          />
                         </div>
                         <div className="col-1 pt-2">
                         {input2.length !== 1 && <BsFillTrashFill style={{'color': 'red', 'cursor': 'pointer'}}
                         className="mr-3"
                         onClick={() => handleRemoveClick(i)}/>}
                        
                         </div>
                         <div className="col-1">
                         {input2.length - 1 === i && <button className="btn btn-success" onClick={handleAddClick}>Add</button>}
                         </div>
                      </div>                    
                    </div>            
                )})}
                <div className="btn-group p-2">
                <button type="submit" class="btn btn-primary mr-2">Config</button>
                <button className="btn btn-primary" onClick={props.handleChang}>Cancel</button>
                </div>
            </form>
        </div>
    )
}
export default BlankTable