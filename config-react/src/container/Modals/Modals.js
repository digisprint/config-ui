import React from 'react'
import {Modals} from '../../components/Modal/Modal'
export const ModalContainer = (props) =>{
    return <Modals {...props}><form>
    <div className="form-group">
      <label htmlFor="formGroupExampleInput">First Name</label>
      <input type="text" className="form-control" id="formGroupExampleInput" placeholder="Enter First Name" />
    </div>
    <div className="form-group">
      <label htmlFor="formGroupExampleInput2">Last Name</label>
      <input type="text" className="form-control" id="formGroupExampleInput2" placeholder="Enter Last Name" />
    </div>
  </form>
  </Modals>
}