<#import "common/bootstrap.ftl" as b>

<@b.page>
<div class="container">
  <form id="contact" action="/updatebookings" method="post">
    <input type="hidden" name="code" value="${code}" />
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="${id}" />
    <h3>Visitation Form</h3>
    <fieldset>
      <input placeholder="who do you want to visit" type="text" class="form-control" name="whomtovisit" id="whomtovisit" value="${booking.whomtovisit}" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Reason For Visit" name="reason" type="text" class="form-control" id="reason" value="${booking.visitreason}" tabindex="2" required>
    </fieldset>
    <fieldset>
      <input type="date" name="visitingdate" class="form-control" id="visitingdate" value="${booking.visitingdate}" tabindex="3" required>
    </fieldset>
    <fieldset>
      <input type="time" name="visittime" class="form-control" id="visittime" value="${booking.visittime}" tabindex="4" required>
    </fieldset>
    <fieldset>
      <input type="text" name="bookingstatus" class="form-control" id="bookingstatus" placeholder="Approval (Approved or Rejected)" value="${booking.bookingstatus}" tabindex="5" required>
    </fieldset>
    <fieldset>
      <input type="hidden" name="date" class="form-control" id="date" placeholder="Reason For Visit" value="${booking.date}" tabindex="6" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Update</button>
    </fieldset>
  </form>
</div>
</@b.page>