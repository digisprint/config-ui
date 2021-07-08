import React from 'react'
import  {Input}  from '../Input/Input'
// import { SearchBar } from '../SearchBar/SearchBar'
import './Tabs.css'

const Tabs = () => {
    return (
        <div style={{'borderTop': '2px solid #eee'}} className="container p-3">
    <main role="main" class="col-md-9 col-lg-10 px-4">
      <div class="justify-content-between flex-wrap flex-md-nowrap pt-3">
        
         {/* Vertical Tab Start */}
             {/* <SearchBar /> */}
             <div class="container d-flex align-items-start">
                  <div class="nav flex-column nav-pills me-3 pr-4" id="v-pills-tab" role="tablist" aria-orientation="vertical" style={{'border-right': '2px solid #eee'}}>
                    <button class="active mb-2 custom-btn" id="v-pills-home-tab" data-bs-toggle="pill" data-bs-target="#v-pills-home" type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">Liverpool</button>
                    <button class="mb-2" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Suburbia</button>
                    <button class="" id="v-pills-messages-tab" data-bs-toggle="pill" data-bs-target="#v-pills-messages" type="button" role="tab" aria-controls="v-pills-messages" aria-selected="false">Williams Sonama</button>
                 </div>
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                        <Input />
                    </div>
                    <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                        <Input />
                    </div>
                    <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                       <Input />
                    </div>
                </div>
              </div>
              
              {/* Vertical Tab Ends */}
      </div>
    </main>
    
            
        </div>
    )
}

export default Tabs