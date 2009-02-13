<style type="text/css" >
		/*
			Vertical Accordions
		*/
		
		.accordion_toggle {
			display: block;
			height: 30px;
			width: 680px;
			background: url(images/accordion_toggle.jpg) no-repeat top right #a9d06a;
			padding: 0 10px 0 10px;
			line-height: 30px;
			color: #ffffff;
			font-weight: normal;
			text-decoration: none;
			outline: none;
			font-size: 12px;
			color: #000000;
			border-bottom: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}
		
		.accordion_toggle_active {
			background: url(images/accordion_toggle_active.jpg) no-repeat top right #e0542f;
			color: #ffffff;
			border-bottom: 1px solid #f68263;
		}
		
		.accordion_content {
			background-color: #ffffff;
			color: #444444;
			overflow: hidden;
		}
			
			.accordion_content h2 {
				margin: 15px 0 5px 10px;
				color: #0099FF;
			}
			
			.accordion_content p {
				line-height: 150%;
				padding: 5px 10px 15px 10px;
			}
			
		.vertical_accordion_toggle {
			display: block;
			height: 30px;
			width: 600px;
			background: url(images/accordion_toggle.jpg) no-repeat top right #a9d06a;
			padding: 0 10px 0 10px;
			line-height: 30px;
			color: #ffffff;
			font-weight: normal;
			text-decoration: none;
			outline: none;
			font-size: 12px;
			color: #000000;
			border-bottom: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}

		.vertical_accordion_toggle_active {
			background: url(images/accordion_toggle_active.jpg) no-repeat top right #e0542f;
			color: #ffffff;
			border-bottom: 1px solid #f68263;
		}

		.vertical_accordion_content {
			background-color: #ffffff;
			color: #444444;
			overflow: hidden;
		}

			.vertical_accordion_content h2 {
				margin: 15px 0 5px 10px;
				color: #0099FF;
			}

			.vertical_accordion_content p {
				line-height: 150%;
				padding: 5px 10px 15px 10px;
			}
  			
		/*
			Horizontal Accordion
		*/
		
		.horizontal_accordion_toggle {
			/* REQUIRED */
			float: left;	/* This make sure it stays horizontal */
			/* REQUIRED */

			display: block;
			height: 100px;
			width: 30px;
			background: url(images/h_accordion_toggle.jpg) no-repeat top left #a9d06a;
			color: #ffffff;
			text-decoration: none;
			outline: none;
			border-right: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}
		
		.horizontal_accordion_toggle_active {
			background: url(images/h_accordion_toggle_active.jpg) no-repeat top left #e0542f;
			border-right: 1px solid #f68263;
		}
		
		.horizontal_accordion_content {
			/* REQUIRED */
			height: 100px;	/* We need to define a height for the accordion as it stretches the width */
			float: left;	/* This make sure it stays horizontal */
			/* REQUIRED */
			
			overflow: hidden;
			background-color: #ffffff;
			color: #444444;
		}
			
			.horizontal_accordion_content p {
				width: 450px;
				line-height: 150%;
				padding: 5px 10px 15px 10px;
			}
					
					
    /* Container styling*/
    #horizontal_container {
      margin: 20px auto 20px auto;
      width: 680px;   
      height: 100px;    
    }
    
    #vertical_nested_container {
      margin: 20px auto 20px auto;
      width: 620px;
    }

	</style>
		<script type="text/javascript">
			
		//
		//  In my case I want to load them onload, this is how you do it!
		// 
		Event.observe(window, 'load', loadAccordions, false);
	
		//
		//	Set up all accordions
		//
		function loadAccordions() {
						
			var bottomAccordion = new accordion('vertical_container');
			
			// Open first one
			bottomAccordion.activate($$('#vertical_container .accordion_toggle')[0]);
			
			// Open second one
			topAccordion.activate($$('#horizontal_container .horizontal_accordion_toggle')[2]);
		}
		
	</script>
<div id="container">
		
    <h1><span>Accordion</span> v2.0</h1>
    
    <p class="description" >Finally a lightweight accordion that is built with scriptaculous and works properly in every browser.</p>
        
	<div id="vertical_container" >

   	<h1 class="accordion_toggle">Changelog</h1>
		<div class="accordion_content">   
      <h2>What's new in v2.0?</h2>
      <p>Well i listened to all you guys out there in my forum and my blog and now all those wishes have been granted!</p>
      <p>
        <ul style="margin: 0 0 0 50px;">
          <li>Open/Close functionality added (Click on an active accordion).</li>
          <li>Nested Vertical Accordions</li>
          <li>Accordions will dynamically resize on content added REAL TIME!</li>
          <li>...lots of bug fixes!</li>
        </ul>
      </p>
    </div>
    
		
		


		<h1 class="accordion_toggle">A Horizontal Accordion! (Nested)</h1>
		<div class="accordion_content">
			
            <h2>Oh yeah, just a few options... BAM Horizontal!</h2>

            
   	</div>
   	
		<h1 class="accordion_toggle">A Vertical Nested Accordion!</h1>
		<div class="accordion_content">

            
            
               
                    <p>
                        Integer commodo nibh sit amet odio. Pellentesque semper. Integer dolor. Donec scelerisque sapien placerat velit. 
                    </p>  	
                
                    <p>
                        Sed at pede vitae turpis porta condimentum. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla facilisi. Morbi erat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; 
                    </p>   	
                
        
                
            
    
   	</div>
   	
		
		
        					
	</div>

	
</div>



