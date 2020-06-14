<#import "common/bootstrap.ftl" as b>

<@b.page>
<div class="container">
  <form id="contact" action="/book" method="post">
    <input type="hidden" name="date" value="${date?c}" />
    <input type="hidden" name="code" value="${code}" />
    <input type="hidden" name="action" value="add">
    <h3>Visitation Form</h3>
    <fieldset>
      <input placeholder="who do you want to visit" type="text" class="form-control" name="whomtovisit" id="whomtovisit" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Reason For Visit" name="reason" type="text" class="form-control" id="reason" tabindex="2" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
    </fieldset>
    <p class="copyright">You might want to check the <a href="/visitingschedules" target="_blank" title="Colorlib">Schedule</a> of whom you want to visit</p>
  </form>
</div>
</@b.page>