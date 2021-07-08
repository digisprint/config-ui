import React, { useState } from 'react'
import axios from 'axios'
import swal from 'sweetalert';
import {Modals} from '../../components/Modal/Modal'
export const ModalContainer = (props) =>{
  const [Input1 , setInput1] = useState('')
  const [input2 , setInput2] = useState('')

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
      console.log('configKeys',formData)
      const PROXY_URL = 'https://cors-anywhere.herokuapp.com/';
                const URL = 'http://35.224.205.52:8080/config/staticKeys';
                const options = {
                    headers: {'X-Requested-With': 'XMLHttpRequest'}
                  };
                  //axios.post('/save', { a: 10 }, options);
                axios.defaults.headers.post['Content-Type'] ='application/json;charset=utf-8';
                axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
            
                axios.put(PROXY_URL + URL, formData , options)
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

    return (<Modals {...props}>
    <form onSubmit={handleForm}>
    <div className="form-group">
      <label htmlFor="formGroupExampleInput">Key</label>
      <input type="text" className="form-control" id="formGroupExampleInput" placeholder="Key" onChange ={handleChanges} value ={Input1} name = 'Input1'/>
    </div>
    <div className="form-group">
      <label htmlFor="formGroupExampleInput2">Value</label>
      <input type="text" className="form-control" id="formGroupExampleInput2" placeholder="Value" onChange ={handleChanges} value ={input2} name ='input2' />
      <hr/>
          <div style={{display:'flex',justifyContent:'space-between'}}>
          <button onClick={props.handleClose} className="btn">Close</button>
          <button type = 'submit' className="btn">Ok</button>
          </div>
    </div>
  </form>
  </Modals>
    )
}