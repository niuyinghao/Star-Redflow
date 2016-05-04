<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>

#    ${pojoNameLower}
<#foreach field in pojo.getAllPropertiesIterator()>
	<#if field.equals(pojo.identifierProperty)>
	<#elseif !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
		<#foreach column in field.getColumnIterator()>
		              <#lt/>${pojoNameLower}.${field.name} =   ${pojoNameLower}.${field.name}
		</#foreach>
	<#elseif c2h.isManyToOne(field)>

	<#elseif c2h.isCollection(field)>
		<#foreach column in field.getColumnIterator()>
		<#--<#lt/>    <form:select cssClass="form-control" path="${field.name}" items="${field.name}List" itemLabel="label" itemValue="value"/>-->
			<#lt/>${pojoNameLower}.${field.name}.${column.name} =   ${pojoNameLower}.${field.name}.${column.name}
		</#foreach>

	<#elseif c2j.isComponent(field)>
	## Section  ${field.name}
		<#foreach column in field.getColumnIterator()>
			<#lt/>${pojoNameLower}.${field.name}.${column.name} =   ${pojoNameLower}.${field.name}.${column.name}
		</#foreach>
	</#if>
</#foreach>
