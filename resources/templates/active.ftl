<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if user?? && (user?size > 0)>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><h4>UserName</h4></th>
                        <th><h4>Status</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <#list actives as actives>
                        <tr>
                            <td style="vertical-align:middle"><h4>${actives.userId}</h4></td>
                            <td style="vertical-align:middle"><h4>${actives.status}</h4></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
</@b.page>