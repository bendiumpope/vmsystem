<div id="sidebar">
    <div class="toggle-btn" onClick="togglesidebar()">
        <span></span>
        <span></span>
        <span></span>
    </div>

    <ul>
        <#if user??>
          <#if profileUrls?? && (profileUrls !="") >
             <span width="5" />
              <li><img src="/static/${profileUrls}" class="img-thumbnail btn-animation profileIm" alt="profile image"/></li>
          <#else>
             <li><img src="/static/profile.png" class="img-thumbnail btn-animation profileIm" alt="profile image"/></li>
          </#if>
          <li>Welcome ${user.userId}</li>
        </#if>
    </ul>

    <ul>
        <li><a class="btn-animation button-primary-override" href="/">Home</a></li>
        <li><a class="btn-animation button-primary-override" href="/about">About</a></li>

        <#if user??>
            <li><a class="btn-animation button-primary-override" href="/book">Book</a></li>
        </#if>
        <#if user?? && user.address =="Admin">
            <li><a class="btn-animation button-primary-override" href="/active">Active Users</a></li>
            <li><a class="btn-animation button-primary-override" href="/users">Users</a></li>
            <li><a class="btn-animation button-primary-override" href="/addschedule">Add Schedule</a></li>
        </#if>
        <#if user?? && (user.address =="VC" || user.address=="HOD")>
            <li><a class="btn-animation button-primary-override" href="/addschedule">Add Schedule</a></li>
        </#if>
        <#if user??>
            <li><a class="btn-animation button-primary-override" href="/allbookings">Bookings</a></li>
            <li><a class="btn-animation button-primary-override" href="/visitingschedules">Schedules</a></li>
            <li><a class="btn-animation button-primary-override" href="/visited">Visit Records</a></li>
            <li><a class="btn-animation button-primary-override" href="/profile">Profile</a></li>
        </#if>
    </ul>
    <div>
        <#if user??>
           <a class="btn-animation button-primary-override" href="/signout">Sign Out</a>
        <#else>
           <a class="btn-animation button-primary-override" href="/signin">Sign In</a>
           <a class="btn-animation button-primary-override" href="/signup">Sign Up</a>
        </#if>
    </div>
</div>

<nav class="navbar navbar-default" id="bgNav">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="#bs-example-navbar-collapse-1">
             <span class="sr-only">Toggle navigation</span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             </button>
             <a class="navbar-brand btn-animation button-primary-override" href="/">VM-SYSTEM</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a class="btn-animation button-primary-override" href="/">Home</a></li>
                <li><a class="btn-animation button-primary-override" href="/about">About</a></li>

                <#if user??>
                  <li><a class="btn-animation button-primary-override" href="/book">Book</a></li>
                </#if>
                <#if user?? && user.address =="Admin">
                        <li><a class="btn-animation button-primary-override" href="/active">Active Users</a></li>
                        <li><a class="btn-animation button-primary-override" href="/users">Users</a></li>
                        <li><a class="btn-animation button-primary-override" href="/addschedule">Add Schedule</a></li>
                </#if>
                <#if user?? && (user.address =="VC" || user.address=="HOD")>
                    <li><a class="btn-animation button-primary-override" href="/addschedule">Add Schedule</a></li>
                </#if>
                <#if user??>
                        <li><a class="btn-animation button-primary-override" href="/allbookings">Bookings</a></li>
                        <li><a class="btn-animation button-primary-override" href="/visitingschedules">Schedules</a></li>
                        <li><a class="btn-animation button-primary-override" href="/visited">Visit Records</a></li>
                        <li><a class="btn-animation button-primary-override" href="/profile">Profile</a></li>
                </#if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <#if user??>
                    <li><p class="navbar-text navbar-text-override">Welcome ${user.userId}</p></li>
                    <li><a class="btn-animation button-primary-override" href="/signout">Sign Out</a></li>
                    <#else>
                    <li><a class="btn-animation button-primary-override" href="/signin">Sign In</a></li>
                    <li><a class="btn-animation button-primary-override" href="/signup">Sign Up</a></li>
                </#if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <#if user??>
                <#if profileUrls?? && (profileUrls !="") >
                  <span width="5" />
                    <img src="/static/${profileUrls}" class="img-thumbnail btn-animation profileIm" alt="profile image"/>
                <#else>
                    <img src="/static/profile.png" class="img-thumbnail btn-animation profileIm" alt="profile image"/>
                </#if>
            </#if>
            </ul>
        </div>
    </div>
</nav>