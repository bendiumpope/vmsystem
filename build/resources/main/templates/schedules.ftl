<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if schedules?? && (schedules?size > 0)>
            <table class="table table-striped">
                <thead>
                    </tr>
                        <th><h4>User Name</h4></th>
                        <th><h4>Office</h4></th>
                        <th><h4>Date To Visit</h4></th>
                        <th><h4>Time To Visit</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <#list schedules as schedule>
                        <tr>
                            <td style="vertical-align:middle"><h4>${schedule.userId}</h4></td>
                            <td style="vertical-align:middle"><h4>${schedule.office}</h4></td>
                            <td style="vertical-align:middle"><h4>${schedule.date}</h4></td>
                            <td style="vertical-align:middle"><h4>${schedule.timeduration}</h4></td>
                            <td class="col-md-1" style="text-align:center; vertical-align:middle;">

                                <form method="post" action="/visitingschedules">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${schedule.id}">
                                    <input type="hidden" name="action" value="edit">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${schedule.id}edit">
                                       Edit
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${schedule.id}edit" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                               <div class="modal-dialog modal-dialog-centered" role="document">
                                                 <div class="modal-content">
                                                   <div class="modal-header">
                                                     <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                                     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                       <span aria-hidden="true">&times;</span>
                                                     </button>
                                                   </div>
                                                   <div class="modal-body">
                                                       <h4>Are you sure you want to make changes?</h4>
                                                   </div>
                                                   <div class="modal-footer">
                                                     <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                     <button type="submit" class="btn btn-danger">Yes</button>
                                                   </div>
                                                 </div>
                                               </div>
                                             </div>
                                </form>
                            </td>

                            <td>
                                <form method="post" action="/visitingschedules">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${schedule.id}">
                                    <input type="hidden" name="action" value="delete">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${schedule.id}">
                                       Delete
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${schedule.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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