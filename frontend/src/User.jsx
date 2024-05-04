import { Header } from './components/Header';
import { Sidebar } from './components/Sidebar';
import { GrClose, GrMenu } from 'react-icons/gr';
import { useState } from 'react';
import { ContentUser } from './Pages/UserPage/ContentUser';

export const User = () => {
    const [sidebar, setSidebar] = useState(false);
  
    const handleSidebar = ()=>{
        setSidebar(!sidebar)
    }
  
    return (
    <div className="min-h-screen flex flex-wrap md:grid grid-col-1 md:grid-col-3 lg:grid-cols-6 ">
        <div className="col-span-5">
            <Header />
            <ContentUser />
        </div>
        <Sidebar sidebar={sidebar} />

        <button onClick={handleSidebar} className="block fixed  bottom-4 right-4 bg-blue-500 p-4 text-white rounded-full text-2xl lg:hidden ">
          {sidebar ? <GrClose />:<GrMenu />}
        </button>
    </div>
  )
}
