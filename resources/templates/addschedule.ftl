<#import "common/bootstrap.ftl" as b>

<@b.page>
<div class="container">
  <form id="contact" action="/addschedule" method="post">
    <input type="hidden" name="code" value="${code}" />
    <input type="hidden" name="action" value="add">
    <h3>Schedule Form</h3>
    <fieldset>
      <input placeholder="Enter your office" name="office" class="form-control" id="office" type="office" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input type="date" name="date" class="form-control" id="date" placeholder="enter date you will be available (17/05/2020)" tabindex="2" required>
    </fieldset>
    <fieldset>
      <input type="text" name="timeduration" class="form-control" id="timeduration" placeholder="enter available time (12:00 AM - 05:00PM)" tabindex="3" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
    </fieldset>
  </form>
</div>

</@b.page>