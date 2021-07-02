import React, { useState } from 'react'
import { BsFillTrashFill } from "react-icons/bs";
export const Input = () => {
    const [inputList, setInputList] = useState([
        { firstName: "", lastName: "" },
])

    const handleChange = (e, index) => {
        const { name, value } = e.target;
        const list = [...inputList];
        list[index] [name] = value;
        setInputList(list);
    }

    const handleAddInput = () => {
        setInputList([...inputList, {firstName: "", lastName: ""}])
    }

    const handleRemoveIput = (index) => {
        const list = [...inputList];
        list.splice(index, 1);
        setInputList(list);
    }
    return (
        <div>
             <div class="container">
                <div class="row">
            {inputList.map((item, i) => (
                    <div class="col-8">
                    <div key={i} class="row input-group mb-3">
                        <div className="col-5">
                         <input 
                         type="text" 
                         class="form-control mr-3" 
                         name="firstName" 
                         placeholder="firstname"
                         value={item.firstName}
                         onChange={e => handleChange(e, i)}
                         />
                         </div>
                         <div className="col-5">
                         <input 
                         type="text" 
                         class="form-control mr-3" 
                         name="lastName" 
                         placeholder="lastname"
                         value={item.lastName}
                         onChange={e => handleChange(e, i)}
                         />
                         </div>
                         <div className="col-1 pt-2">
                         {inputList.length !== 1 && <BsFillTrashFill 
                         style={{'color': 'red', 'cursor': 'pointer'}}
                          className="mr-3"
                         onClick={() => handleRemoveIput(i)}/>} 
                        
                         </div>
                         <div className="col-1">
                        {inputList.length - 1 === i && <input 
                         type="button" 
                         class="btn" 
                         value="Add"
                         onClick={handleAddInput}
                         /> }
                         </div>
                    </div>
                    
                    </div>
              
            ))}
            
              </div>
              <div className="bdr-top pt-2" style={{'border-top':'2px solid #eee'}}>
                  <button type="button" className="btn btn-primary mr-3">Upadate</button>
                  <button type="button" className="btn btn-primary">Remove All</button>
              </div>
            </div>
        </div>
    )
}
