import React, { useState } from 'react'
import { BsFillTrashFill } from "react-icons/bs";
import axios from 'axios'
import swal from 'sweetalert';


function StaticBlankTable (props) {
    const [Input1 , setInput1] = useState('')
    const [input2 , setInput2] = useState('') 
    console.log(props)

    const handleChanges =(e)=>{
        const input = e.target.name
        if(input === "Input1"){
            setInput1(e.target.value)
        } else if(input === "input2"){
            setInput2(e.target.value)
        }
        }
    

    const handleForm =(e)=>{
        e.preventDefault()
        const formData = {
          
            staticKeys : {
               key : Input1,
               value : input2
                  }
           }
        //console.log(formData)

        const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                const URL = 'http://35.224.205.52:8080/config/staticKeys';
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
                setInput2('')

}

//  // handle click event of the Remove button
//  const handleRemoveClick = index => {
//     const list = [...input2];
//     list.splice(index, 1);
//     setInput2(list);
//   };

// // handle click event of the Add button
// const handleAddClick = () => {
//     setInput2([...input2, { value: ''}]);
//   };
  
//   // handle input change
//   const handleInputChange = (e, index) => {
//     const { name, value } = e.target;
//     const list = [...input2];
//     list[index][name] = value;
//     setInput2(list);
//   };
    
    return(
        <div>
            <form onSubmit ={handleForm}>
                <input type ="text" 
                placeholder = 'Enter the key' 
                className ="form-control" onChange ={handleChanges} 
                value ={Input1} name = 'Input1'
                style={{'width': '35%'}}
                />

                <input type ="text" placeholder = 'Enter the value' className= "form-control mt-2" 
                onChange ={handleChanges} value ={input2} name ='input2'
                style={{'width': '35%'}}
                />
                <div className="btn-group p-2">
                <button type="submit" class="btn btn-primary mr-2">Static</button>

                <button className="btn btn-primary" onClick={props.toggle}>Cancel</button>
                </div>
            </form>
        </div>
    )
    }
export default StaticBlankTable