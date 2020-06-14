<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if visitrecords?? && (visitrecords?size > 0)>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><h4>UserName</h4></th>
                        <th><h4>Date</h4></th>
                        <th><h4>Time In</h4></th>
                        <th><h4>Visited</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <#list visitrecords as visitrecord>
                        <tr>
                            <td style="vertical-align:middle"><h4>${visitrecord.userId}</h4></td>
                            <td style="vertical-align:middle"><h4>${visitrecord.date}</h4></td>
                            <td style="vertical-align:middle"><h4>${visitrecord.timein}</h4></td>
                            <td style="vertical-align:middle"><h4>${visitrecord.visitedwhom}</h4></td>
                            <td class="col-md-1" style="text-align:center; vertical-align:middle;">

                                <form method="post" action="/visited">
                                    <input type="hidden" name="date" value="${date?c}">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${visitrecord.id}">
                                    <input type="hidden" name="action" value="delete">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${visitrecord.id}">
                                       Delete
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${visitrecord.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                               <div class="modal-dialog modal-dialog-centered" role="document">
                                                 <div class="modal-content">
                                                   <div class="modal-header">
                                                     <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                                     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                       <span aria-hidden="true">&times;</span>
                                                     </button>
                                                   </div>
                                                   <div class="modal-body">
                                                       <h4>Are you sure?</h4>
                                                       <h5>Action Cant be undone</h5>
                                                   </div>
                                                   <div class="modal-footer">
                                                     <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                     <button type="submit" class="btn btn-danger">Ok</button>
                                                   </div>
                                                 </div>
                                               </div>
                                             </div>
                                </form>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
</@b.page>