<#import "common/bootstrap.ftl" as b>

<@b.page>
    <#if bookings?? && (bookings?size > 0)>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><h4>User</h4></th>
                        <th><h4>Whom To Visit</h4></th>
                        <th><h4>Reason</h4></th>
                        <th><h4>Date To Visit</h4></th>
                        <th><h4>Time To Visit</h4></th>
                        <th><h4>Booking Status</h4></th>
                        <th><h4>Date Booked</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <#list bookings as booking>
                        <tr>
                            <td style="vertical-align:middle"><h4>${booking.userId}</h4></td>
                            <td style="vertical-align:middle"><h4>${booking.whomtovisit}</h4></td>
                            <td style="vertical-align:middle"><h4>${booking.visitreason}</h4></td>
                            <td style="vertical-align:middle"><h4>${booking.visitingdate}</h4></td>
                            <td style="vertical-align:middle"><h4>${booking.visittime}</h4></td>
                            <td style="vertical-align:middle"><h4>${booking.bookingstatus}</h4></td>
                            <td style="vertical-align:middle"><h4>${booking.date}</h4></td>
                            <td class="col-sm-1" style="text-align:center; vertical-align:middle;">

                            <#if user?? && (user.address =="Admin" || user.address == "Secretary")>

                                <form method="post" action="/allbookings">
                                    <input type="hidden" name="date" value="${date?c}">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${booking.id}">
                                    <input type="hidden" name="action" value="edit">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${booking.id}edit">
                                       Edit
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${booking.id}edit" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                                <form method="post" action="/allbookings">
                                    <input type="hidden" name="date" value="${date?c}">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${booking.id}">
                                    <input type="hidden" name="action" value="completed">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#${booking.id}completed">
                                       Completed
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${booking.id}completed" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                                                       <h5>${booking.userId} visited ${booking.whomtovisit}</h5>
                                                   </div>
                                                   <div class="modal-footer">
                                                     <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                     <button type="submit" class="btn btn-primary">Yes</button>
                                                   </div>
                                                 </div>
                                               </div>
                                             </div>
                                </form>
                            </#if>
                            </td>
                            <td class="col-sm-1" style="text-align:center; vertical-align:middle;">
                                <form method="post" action="/allbookings">
                                    <input type="hidden" name="date" value="${date?c}">
                                    <input type="hidden" name="code" value="${code}">
                                    <input type="hidden" name="id" value="${booking.id}">
                                    <input type="hidden" name="action" value="delete">
                                  <!-- Button trigger modal -->
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${booking.id}">
                                       Delete
                                     </button>
                                            <!-- Modal -->
                                             <div class="modal fade" id="${booking.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
        <h4><p class="copyright">Schedule an <a href="/book" target="_blank" title="Colorlib">appointment.</a></p></h4>

</@b.page>