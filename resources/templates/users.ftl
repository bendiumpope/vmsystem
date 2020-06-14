<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if users?? && (users?size > 0)>
            <table class="table table-striped">
                <thead>
                    </tr>
                        <th><h4>UserName</h4></th>
                        <th><h4>SurName</h4></th>
                        <th><h4>FirstName</h4></th>
                        <th><h4>OtherName</h4></th>
                        <th><h4>Email</h4></th>
                        <th><h4>Phone No.</h4></th>
                        <th><h4>Address</h4></th>
                        <th><h4>Role</h4></th>
                        <th><h4>password Hash</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as user>
                        <tr>
                            <td style="vertical-align:middle"><h5>${user.userId}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.surName}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.firstName}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.otherName}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.emailAddress}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.phoneNumber}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.address}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.role}</h5></td>
                            <td style="vertical-align:middle"><h5>${user.passwordHash}</h5></td>
                            <td class="col-1" style="text-align:center; vertical-align:middle;">

                                <form method="post" action="/users">
                                    <input type="hidden" name="date" value="${date?c}">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${user.userId}">
                                    <input type="hidden" name="action" value="edit">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${user.userId}edit">
                                       Edit
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${user.userId}edit" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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

                            <td class="col-sm-1" style="text-align:center; vertical-align:middle;">
                                <form method="post" action="/users">
                                    <input type="hidden" name="date" value="${date?c}">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${user.userId}">
                                    <input type="hidden" name="action" value="delete">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${user.userId}">
                                       Delete
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${user.userId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                                                     <button type="submit" class="btn btn-danger" border="0" alt="Delete">Ok</button>
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