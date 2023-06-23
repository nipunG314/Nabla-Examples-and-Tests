import org.DevshGraphicsProgramming.Agent
import org.DevshGraphicsProgramming.BuilderInfo
import org.DevshGraphicsProgramming.IBuilder

class CExampleBuilder extends IBuilder
{
	public CExampleBuilder(Agent _agent, _info)
	{
		super(_agent, _info)
		
		targetBaseName = _targetBaseName
		projectPathRelativeToNabla = _projectPathRelativeToNabla
	}
	
	@Override
	public boolean prepare(Map axisMapping)
	{
		return true
	}
	
	@Override
  	public boolean build(Map axisMapping)
	{
		IBuilder.CONFIGURATION config = axisMapping.get("CONFIGURATION")
		IBuilder.BUILD_TYPE buildType = axisMapping.get("BUILD_TYPE")
		
		def nameOfBuildDirectory = getNameOfBuildDirectory(buildType)
		def nameOfConfig = getNameOfConfig(config)
		
		agent.execute("cmake --build ../../${nameOfBuildDirectory}/${info.targetProjectPathRelativeToRoot} --target ${info.targetBaseName} --config ${nameOfConfig} -j12 -v")
		
		return true
	}
	
	@Override
  	public boolean test(Map axisMapping)
	{
		return true
	}
	
	@Override
	public boolean install(Map axisMapping)
	{
		return true
	}
}

def create(Agent _agent, _info)
{
	return new CExampleBuilder(_agent, _info)
}

return this