import { useAssemblies } from '../../hooks/useAssemblies';
import { AssemblyFragmentUser } from '../../components/AssemblyFragmentUser';
import { Link } from 'react-router-dom';

export const ContentUser = () => {

  const [assemblies, setAssemblies] = useAssemblies({ url: "http://localhost:8080/assembly/all" });
  return (
    <div className='px-2 pt-14 w-full h-full bg-gray-50'>
      <ul>
        {assemblies && assemblies.map(assembly => (
          <Link key={assembly.assemblyID} to={`/user/assemblyId/${assembly.assemblyID}`}>
            <AssemblyFragmentUser assembly={assembly} />
          </Link>
        ))}
        
      </ul>
    </div>
  );
};
